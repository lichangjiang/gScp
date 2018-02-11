package com.lcj.gradle.plugin.upload.extensition

import java.io.File

import org.gradle.api.Action

import scala.beans.BeanProperty

/**
  * Created by lichangjiang on 18-2-11.
  */
class ScpToExtensition (@BeanProperty var userName:String,
                        @BeanProperty var host:String,
                        @BeanProperty var port:Int = 22,
                        @BeanProperty var pwd:String,
                        @BeanProperty var from:File,
                        @BeanProperty var to:String){

  def this() = this(null,null,22,null,null,null)


  override def toString = s"Scp ${from.getName} To $userName@$host:$port/$to"
}
