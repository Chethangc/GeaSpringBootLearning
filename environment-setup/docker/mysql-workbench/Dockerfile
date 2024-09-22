FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

# Install required packages
RUN apt-get update 
RUN apt-get install -y \
    wget \
    libatkmm-1.6-1v5 \
    libglibmm-2.4-1v5 \
    libgtk2.0-0 \
    libgtkmm-3.0-1v5 \
    libmysqlclient21 \
    libodbc2 \
    libproj25 \
    libpython3.10 \
    libsigc++-2.0-0v5 \
    libzip4 \
    libglx0 \
    libopengl0 \
    libsecret-1-0 \
    libpython3.12 \
    dbus-x11 \
    gnome-keyring \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Adding mysqlworkbenchuser.
RUN useradd -m mysqlworkbenchuser 

# Download MySQL Workbench .deb file
WORKDIR /opt/deb/
RUN wget https://dev.mysql.com/get/Downloads/MySQLGUITools/mysql-workbench-community_8.0.38-1ubuntu24.04_amd64.deb

# Install MySQL Workbench
RUN apt-get update \
    && dpkg -i /opt/deb/mysql-workbench-community_8.0.38-1ubuntu24.04_amd64.deb \
    || apt-get install -f -y

# Remove MySQL Workbench .deb file
RUN rm -r /opt/deb/mysql-workbench-community_8.0.38-1ubuntu24.04_amd64.deb

# Change the user mysqlworkbenchuser
USER mysqlworkbenchuser

# RUN MySQL Workbench
CMD ["dbus-launch", "mysql-workbench"]
