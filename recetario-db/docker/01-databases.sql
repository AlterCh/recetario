
# create databases
CREATE DATABASE IF NOT EXISTS `recetario`;

# create root user and grant rights
GRANT ALL ON *.* TO 'root'@'%';
