### DEV BOX:CREATE ANGULAR PROJ
```
ng new testsite
cd testsite
ng add @nguniversal/express-engine --clientProject testsite
npm run build:ssr
npm run serve:ssr
Remember to deploy from dist/
TEST: npm run build:ssr
SERVER:
mkdir /var/www/testsite.co
sudo chown -R dean:dean /var/www/testsite.co
DEV BOX
scp -r ~/Projects/LBRY/testsite/dist/ dean@192.168.0.110:/var/www/testsite/
SERVER:
sudo apt install apache2
sudo a2enmod proxy
sudo a2enmod proxy_http
sudo systemctl restart apache2
cd /etc/apache2/sites-available
sudo a2dissite 000-default.conf
sudo nano testsite.co.conf

<VirtualHost *:80>  
    ServerName testsite.co  
    ServerAlias site.testsite.co  
    #DocumentRoot /var/www/testsite.co/  
    ProxyPreserveHost On  
    ProxyRequests Off  
    ProxyPass / http://localhost:4000  
    ProxyPassReverse / http://localhost:4000/  
    ErrorLog /var/www/testsite.co/error.log  
    CustomLog /var/www/testsite.co/requests.log combined  
</VirtualHost>  
sudo a2ensite testsite.co.conf
sudo systemctl restart apache2
sudo apt install nodejs
Test in /var/www/testsite.co directory: node server.js
```
SERVER:
`sudo nano /etc/systemd/system/testsite.service`

[Unit]
```
Description=TestSite.co  
StartLimitInterval=15  
StartLimitBurst=15  
#After=apache2.service  
```
[Service]
```
User=dean  
WorkingDirectory=/var/www/testsite.co  
Type=simple  
ExecStart=/usr/bin/node /var/www/testsite.co/server.js  
Restart=always  
RestartSec=10
```
[Install]  
```
WantedBy=multi-user.target  
sudo systemctl enable testsite.service
sudo systemctl daemon-reload
sudo systemctl start testsite.service
```