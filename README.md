# Sample Spring Boot Web App with Systemd

## Run

```sh
mvn clean package
java -jar target/spring-boot-systemd-sample-0.0.1-SNAPSHOT.jar 
```

## Run with Systemd

Copy files.

```sh
sudo cp systemd/start-spring-boot-systemd-sampleapp.sh /opt/
sudo cp target/spring-boot-systemd-sample-0.0.1-SNAPSHOT.jar /opt/
```

Add user then change file owner and permission.

```sh
sudo useradd -m sampleapp

sudo chown sampleapp:sampleapp /opt/start-spring-boot-systemd-sampleapp.sh
sudo chown sampleapp:sampleapp /opt/spring-boot-systemd-sample-0.0.1-SNAPSHOT.jar

sudo chmod 500 /opt/start-spring-boot-systemd-sampleapp.sh
sudo chmod 400 /opt/spring-boot-systemd-sample-0.0.1-SNAPSHOT.jar
```

Copy unit file to the target directory.

```sh
sudo cp systemd/spring-boot-systemd-sampleapp.service /etc/systemd/system
```

Start service.

```sh
sudo systemctl enable spring-boot-systemd-sampleapp.service 
sudo systemctl start spring-boot-systemd-sampleapp.service 
```

## Testing

Check created service status.

```sh
systemctl status spring-boot-systemd-sampleapp.service
● spring-boot-systemd-sampleapp.service - "Sample Application for running spring boot app with systemd"
Loaded: loaded (/etc/systemd/system/spring-boot-systemd-sampleapp.service; enabled; vendor preset: enabled)
Active: active (running) since ...
```

Check web application status.

```sh
curl http://localhost:18888
{"message": "hello"}
```

The service should start automatically after the machine is rebooted.

## Cleanup

Remove created service.

```sh
sudo systemctl stop spring-boot-systemd-sampleapp.service 
sudo systemctl disable spring-boot-systemd-sampleapp.service
sudo rm /etc/systemd/system/spring-boot-systemd-sampleapp.service
sudo systemctl daemon-reload 
sudo systemctl reset-failed
```

Remove copied files and created user.

```sh
sudo rm /opt/spring-boot-systemd-sample-0.0.1-SNAPSHOT.jar
sudo rm /opt/start-spring-boot-systemd-sampleapp.sh
```

```sh
sudo userdel -r sampleapp
```
