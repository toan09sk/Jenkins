- version ssh: `ssh -V`
- vào server và show user: `whoami`
- utunbu version: `cat /etc/os-release`
- kiểm tra ssh đã dc cài đặt chưa: `systemctl status sshd`
- dừng ssh: `systemctl stop sshd`
- remove ssh: `systemctl openssh-server`

- để cài đặt ssh trên CentOS: `yum install openssh openssh-server openssh-clients openssl-libs -y`
- để cài đặt ssh Utunbu : `sudo apt-get install openssh-server openssh-client`
- Chạy dịch vụ SSH Server, thiết lập chạy ngay khi khởi động
`systemctl start sshd`
`systemctl enable sshd`
- Mặc định SSH dùng cổng 22 nên bạn phải mở cổng này nếu có firewall
`firewall-cmd --add-port=22/tcp --permanent`
`firewall-cmd --reload`
Nếu sau này bạn cấu hình đổi cổng SSH mặc định, bạn cần mở cổng đó (thay 22 bằng cổng của bạn)\
File cấu hình của OpenSSH Server tại ***/etc/ssh/sshd_config***, bạn có thể mở và chỉnh sửa nó theo nhu cầu (có thể dùng Vim, Nano để soạn thảo). Sau khi sửa Config thì cần khởi động lại SSH Server.
`systemctl restart sshd`

### Cấu hình dịch vụ OpenSSH Server
- /etc/ssh/sshd_config trên Linux
- C:\OpenSSH\sshd_config_default trên Windows (Theo đường dẫn thư mục bạn cài OpenSSH)

1. Đổi cổng mặc định nếu muốn đổi cổng kết nối SSH, ví dụ đổi sang cổng 2233
`Port 1235`

2. Bật chế độ xác thực SSH Key
`PubkeyAuthentication yes`

3. Chỉ ra đường dẫn lưu Public key
`AuthorizedKeysFile .ssh/authorized_keys`

4. Tắt xác thực bằng Password
`PasswordAuthentication no`

5. Không cho tài khoản root đăng nhập
`PermitRootLogin no`

### Đăng nhập vào server slave
`ssh root@3.19.54.241`
`ssh root@3.19.54.241 -p 22`
- Sẽ show log  `ssh -v root@3.19.54.241 -p 22`
Lần đầu tiên, sau khi bạn nhập password,nó sẽ yêu cầu 1 fingerprintf. Nếu sau này ta xóa hệ đều hành CentOS đi ta cài lại CentOS khác thì khi kết nối đến mặc dù địa chỉ Ip vẫn như vậy, tài khoản vẫn là như vậy nhưng fingerprintf thì không cho phép truy cập\
Ta mở file know_hosts: `ls ~/.ssh/` và `ls ~/.ssh/know_hosts` và `cat ~/.ssh/know_hosts`
Ta xóa cái fingerPrint cũ đi\

finger printf là mà the dõi, nó đảm bảo nếu lần sau ta kết nối thì nó sẽ đến đúng cái server mà lần trước chúng ta kết nối đến

### Lệnh SCP (secure copy), upload và download các file tới Server bằng giao thức SSH

 là lệnh do OpenSSH Client cung cấp, nó cho phép truyền tải file qua lại giữa máy local và remote (server), nó sử dụng giao thức SSH để truyền file. Bạn sử dụng đến lệnh scp khi muốn:

- Copy file,thư mục, từ máy local lên server
- Copy file,thư mục, từ máy server (remote) về local (client)
- Copy file,thư mục, từ máy server (remote) này sang máy server (remote) khác\
Cú pháp cơ bản như sau:

scp thumucnguon thumucdich
user@server_ip:/path/
scp [OPTION] [user_src@]src_host:]src_file [user@]desk_host:]des_file\

Nếu copy tất cả các thư mục cả cha lẫn con thì dùng tham số -r

Example:
 `scp -r ~/Desktop/learn-ssh/ root@3.19.54.241:/home/data/`

 ### Cơ chế xác thực bằng SSH Key
 - *Public Key* khóa chung, là một file text - nó lại lưu ở phía Server SSH, nó dùng để khi Client gửi Private Key (file lưu ở Client) lên để xác thực thì kiểm tra phù hợp giữa Private Key và Public Key này. Nếu phù hợp thì cho kết nối.
 - *Private Key* khóa riêng, là một file text bên trong nó chứa mã riêng để xác thực (xác thực là kiểm tra sự phù hợp của Private Key và Public Key). Máy khách kết nối với máy chủ phải chỉ ra file này khi kết nối SSH thay vì nhập mật khẩu. Hãy lưu file Private key cận thận, bất kỳ ai có file này có thể thực hiện kết nối đến máy chủ của bạn

  - Điều chỉnh trong file config ssh: `vi /etc/ssh/sshd_config`
   + pubkeyAuthentication true\
   + AuthorizeKeyFile  .ssh/authorized_keys  <--- nơi lưu public key.\ 
   Nếu user là root --> /root/.ssh/authorized_keys\
   Nếu user là abc --> /home/abc/.ssh/authorized_keys
   
   Tạo thư mục .ssh cho root: `mkdir /root/.ssh`
   Tạo ssh public + private key : `ssh-keygen -t rsa`
   Copy file *id_rsa.pub* vào *root*: `cp id_rsa.pub /root/.ssh/authorized_keys`
   Check mode cho file authorized_key:`cmod 600 /root/.ssh/authorized_keys`
   Check mode cho thư mục ssh: `cmod 700 /root/.ssh`
   Check mode cho thư mục root: `cmod 700 /root`
   Remove id_rsa đi: `rm id_rsa.pub`
   có th61 cần: ` mv id_rsa ~/keys`
   File private key *id_rsa* lấy về lưu về máy trạm
   Ta lấy file id_rsa từ tài khoản root: `scp root@3.19.54.241:/keys/id_rsa ~/Desktop`

   ### Đăng nhâp bằng file ssh
   - Tại máy trạm có 1 file config ở thư mục `ls ~/.ssh/` trong này có 1 file config `ls ~/.ssh/config`. Mở file lên `vi ~/.ssh/config`

   `Host 3.19.54.241
     User root
     Port 22
     PreferredAuthentications publickey
     IdentityFile "/Users/xuanthulab/Desktop/id_rsa"`

    - Tạo file config trên window: `ni config`



