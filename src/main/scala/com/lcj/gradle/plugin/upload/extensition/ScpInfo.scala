package com.lcj.gradle.plugin.upload.extensition


import org.gradle.api.Action

import scala.beans.BeanProperty

/**
  * Created by lichangjiang on 18-2-11.
  */
class ScpInfo {

  @BeanProperty var isScpTo = true
  @BeanProperty var scpToExtensition : ScpToExtensition = new ScpToExtensition

  def scpTo(action : Action[_ >: ScpToExtensition]) = {
    action.execute(scpToExtensition)
  }



}
