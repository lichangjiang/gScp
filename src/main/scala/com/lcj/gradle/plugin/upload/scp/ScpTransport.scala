package com.lcj.gradle.plugin.upload.scp

import java.io._

import com.jcraft.jsch.{ChannelExec, JSch, UserInfo}


/**
  * Created by lichangjiang on 18-2-11.
  */
abstract class ScpTransport(userName : String,
                   host : String,
                   port : Int,
                   pwd : String){

  require(userName != null)
  require(host != null)
  require(pwd != null)

  def execute() = {
    val session = jschSession
    session.setUserInfo(userInfo)
    session.connect()
    val channel = session.openChannel("exec")
    setCommand(channel.asInstanceOf[ChannelExec])

    val out = channel.getOutputStream
    val in = channel.getInputStream

    channel.connect(10*1000)

    if(chechAck(in) != 0) throw new RuntimeException(s"failed to connect to remote ssh $userName@$host$port")
    transport(in,out)

    out.close()
    channel.disconnect()
    session.disconnect()
    println("finish scp transport")
  }

  def setCommand(channel:ChannelExec)
  def transport(inputStream : InputStream,outputStream : OutputStream)

  def jschSession =  new JSch().getSession(userName,host,port)

  def chechAck(is : InputStream) : Int = {
    val b = is.read()
    b match {
      case _ if(b == 1 || b==2) =>
        println(new BufferedReader(new InputStreamReader(is)).readLine())
        b
      case _ => b
    }
  }

  def userInfo : UserInfo = {
    new UserInfo {
      override def promptPassword(s: String): Boolean = {
        println(s)
        true
      }

      override def promptYesNo(s: String): Boolean ={
        println(s)
        true
      }

      override def showMessage(s: String): Unit = println(s)

      override def getPassword: String = pwd

      override def promptPassphrase(s: String): Boolean = {
        println(s)
        true
      }

      override def getPassphrase: String = null
    }
  }
}
