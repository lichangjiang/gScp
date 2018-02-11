package com.lcj.gradle.plugin.upload.scp

import java.io._

import com.jcraft.jsch.ChannelExec

/**
  * Created by lichangjiang on 18-2-11.
  */
class ScpTo(userName : String,
            host : String,
            port : Int,
            pwd : String,
            from : File,
            to:String) extends ScpTransport(userName,host,port,pwd){

  override def setCommand(channel: ChannelExec): Unit = channel.setCommand(s"scp -t $to")

  override def transport(inputStream: InputStream, outputStream: OutputStream): Unit = {

    val fileSize = from.length()
    val content = new StringBuilder
    content.append(s"C0644 $fileSize ")
    if(from.getName.lastIndexOf("/") > 0) content.append(from.getName.substring(from.getName.lastIndexOf("/")+1))
    else content.append(from.getName)
    content.append("\n")
    outputStream.write(content.toString().getBytes())
    outputStream.flush()
    if(chechAck(inputStream) != 0) throw new RuntimeException("send file name failed")

    val fileStream = new BufferedInputStream(new FileInputStream(from))
    val buf = new Array[Byte](1024)
    var n : Int = 0
    while({n = fileStream.read(buf,0,buf.length);n >0}){
      outputStream.write(buf,0,n)
    }
    fileStream.close()
    buf(0) = 0;outputStream.write(buf,0,1);outputStream.flush() // send '\0'
    if(chechAck(inputStream) != 0) throw new RuntimeException("send file content failed")
  }
}
