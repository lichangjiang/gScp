package com.lcj.gradle.plugin.upload.task

import com.lcj.gradle.plugin.upload.extensition.ScpInfo
import com.lcj.gradle.plugin.upload.scp.ScpTo
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
  * Created by lichangjiang on 18-2-11.
  */
class ScpToTask extends DefaultTask{


  @TaskAction
  def action = {
    val scpInfo = getProject.getExtensions.getByType(classOf[ScpInfo])
    if(scpInfo.isScpTo){
      println(s"${scpInfo.scpToExtensition}")
      val scpTo = new ScpTo(scpInfo.scpToExtensition.userName,
        scpInfo.scpToExtensition.host,
        scpInfo.scpToExtensition.port,
        scpInfo.scpToExtensition.pwd,
        scpInfo.scpToExtensition.from,
        scpInfo.scpToExtensition.to)
      scpTo.execute()
    }else{
      getProject.getLogger.error("not implement other type but ScpTo")
    }

  }

}
