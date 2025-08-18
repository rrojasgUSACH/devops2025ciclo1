terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
  }
}

provider "docker" {}

# Red propia opcional
resource "docker_network" "app" {
  name = "conversor_net"
}

# Construye la imagen desde el Dockerfile local
resource "docker_image" "conversor" {
  name = "conversor:local"

  build {
    # sube dos niveles desde infra/terraform hasta la raíz del repo
    context    = abspath("${path.module}/../..")
    dockerfile = "Dockerfile"   # siempre relativo al context
  }

  keep_locally = true
}


# Despliega el contenedor local mapeando 8080
resource "docker_container" "app" {
  name  = "conversor_app"
  image = docker_image.conversor.name

  networks_advanced {
    name = docker_network.app.name
  }

  ports {
    internal = 8080
    external = 8080
  }

  # Variables JVM opcionales
  env = [
    "JAVA_OPTS=-Xms128m -Xmx256m"
  ]

  # Nota: el healthcheck interno requeriría curl/wget en la imagen.
  # Mantenemos la espera externa en el workflow para simplificar.
}
