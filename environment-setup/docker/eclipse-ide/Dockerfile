FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

# Installing required packages
RUN apt-get update 
RUN apt-get install -y \
    wget \
    software-properties-common \
    openjdk-11-jdk \
    libgtk-3-0 \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxtst6 \
    libfreetype6 \
    libxinerama1 \
    libxrandr2 \
    libglu1-mesa \
    xauth \
    xorg \
    x11-apps \
    libgtk-3-dev \
    libnss3 \
    libxt-dev \
    libcanberra-gtk3-dev \
    dbus-x11 \
    gnome-keyring \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Adding eclipseuser.
RUN useradd -m eclipseuser \
     && mkdir -p /opt/eclipse \
     && chown -R eclipseuser:eclipseuser /opt/eclipse
USER eclipseuser

# Download and install Eclipse
RUN wget -O /tmp/eclipse.tar.gz \
    https://mirror.kakao.com/eclipse/technology/epp/downloads/release/2024-09/R/eclipse-jee-2024-09-R-linux-gtk-x86_64.tar.gz 
 
RUN tar -xzf /tmp/eclipse.tar.gz -C /opt/ \
    && rm /tmp/eclipse.tar.gz

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

CMD ["dbus-launch", "/opt/eclipse/eclipse -debug"]
