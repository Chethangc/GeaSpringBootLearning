#! /bin/bash
# NOTE: Run this script as root only

apt install default-jre
wget http://ftp.yz.yamagata-u.ac.jp/pub/eclipse/oomph/epp/2024-09/R/eclipse-inst-jre-linux64.tar.gz
mkdir eclipse-jre
tar -xvf eclipse-inst-jre-linux64.tar.gz -C eclipse-jre
cd eclipse-jre/eclipse-installer
./eclipse-inst
rm -rf eclipse-inst-jre-linux64.tar.gz
