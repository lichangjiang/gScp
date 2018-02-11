package com.lcj.gradle.plugin.upload

import com.lcj.gradle.plugin.upload.extensition.ScpInfo
import com.lcj.gradle.plugin.upload.task.ScpToTask
import org.gradle.api.{Action, Plugin, Project}

/**
  * Created by lichangjiang on 18-2-10.
  */
class ScpPlugin extends Plugin[Project]{
  override def apply(project: Project): Unit = {
    project.getExtensions.add("scpInfo",classOf[ScpInfo])
    project.getTasks.create("scpToTask",classOf[ScpToTask])


  }
}
