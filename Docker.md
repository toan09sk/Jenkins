### 🏃🏼‍♀️ Lab document
- Cài docker và docker-compose trên EC2/AWS
```
sudo yum update -y
sudo yum install -y docker
sudo service docker start
sudo usermod -a -G docker ec2-user
```

- Cài docker-compose
```
sudo curl -L "https://github.com/docker/compose/rel... -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

- Trợ giúp trong docker
```
docker
docker image --help
docker image save --help
```

- Xem trong hệ thống có những IMAGE nào, và IMAGE đó lấy từ đâu ra
```
docker images
docker search ubuntu
docker pull ubuntu:16.04
docker pull ubuntu
docker image rm ubuntu:16.04
docker image rm af
```
- Kho docker image https://hub.docker.com/

- Thực hành
```
docker search centos
docker pull centos
```
- Khởi tạo 1 container, với it là nhận input và kết nối terminal
```
docker run -it ubuntu:latest
docker ps
docker ps -a
```
- Khởi tạo container với name và hotname
`docker run -it --name "ABC" -h centos1 centos:latest`
- Xem thông tin ubuntu
`cat /etc/*release`
- Khi container đang dừng ta muốn khởi chạy lại
`docker start 89`
- Khi muốn vào lại ubuntu
`docker attach 89`
- Muốn thoát terminal container mà vẫn muốn container vẫn chạy `ctrl + p + q`
- Ở ngoài container nhưng muốn nó dừng lại: `docker stop 89`
- Muốn xóa 1 container
 ```
 docker rm 89
 docker rm -f ABC
 ```

### Lệnh Docker exec, lưu container thành image với commit, xuất image ra file

- Ta ở ngoài nhưng vẫn muốn thực hiện 1 lệnh cho container đang chạy
```
docker run -it --name U1 -h ubuntu1 ubuntu
docker exec U1 ls -la
docker exec -it U1 bash
```
- Chú ý: khi exit khỏi bash thì container vẫn chạy
- để xem các tiến trình trong container đang chạy ta cần lệnh htop
- Cài thêm 1 số gói cho container U1
```
apt update -y
apt install htop
apt search iputils
apt install iputils-ping
ping google.com
apt install vim
vi abc
```
- container U1 này đã cài đặt 1 số package. Bây h ta muốn lưu container này - ta sẽ commit container thành 1 IMAGE
- Ta chú ý khi commit thì container ấy phải có trạng thái dừng
```
docker commit U1 ubuntu-vim:version1
docker rm U1
docker run -it ubuntu-vim:version1
```

- Lưu IMAGE thành 1 file trong máy host
```
cd /home/imageRep
docker save --output myimage.tar ubuntu-vim:version1
docker image rm ubuntu-vim:version1 -f
docker load -i myimage.tar
docker tag 466 newimage:version2
docker image rm ubuntu-vim:version1
```


