gScp is a [Gradle](https://gradle.org/) plugin that simplifies upload file  to remote ssh server using scp (using [Jsch](http://www.jcraft.com/jsch/))

## Getting started
```
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.lcj.gradle.plugin:uploadplugin:0.1-SNAPSHOT"
  }
}

apply plugin: "com.lcj.gradle.plugin.upload"

scpInfo {
    scpTo {
        userName = "YOUR_USER_NAME"
        host = "YOUR_SSH_SERVER_HOST"
        port = YOUR_SSH_PORT
        pwd = "YOUR_PASSWORD"
        from = file("THE_FILE_PATH_UPLOAD_TO_SSH_SERVER")
        to = "YOUR_REMOTE_FILE_PATH"
    }
}

run: gradle scpToTask
```

An example could be:
```
    scpInfo {
        isScpTo = true
        scpTo {
            userName = "jack"
            host = "8.8.8.8"
            port = 22
            pwd = "123456"
            from = file("/home/jack/file.txt")
            to = "/home/remote"
        }
    }
```