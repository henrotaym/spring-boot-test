FROM ubuntu:24.10

ARG TARGETPLATFORM
ARG BUILDPLATFORM
ARG JDKVERSION="23.0.1"

# Set working directory
WORKDIR /opt/apps/app

# Download java binaries
RUN arch=$(echo ${TARGETPLATFORM} | sed 's/.*\///') && \
    if [ $arch = "amd64" ]; \
        then jdkArch="x64"; \
        else jdkArch="aarch64"; \
    fi && \
    apt-get update && \
    apt-get install -y wget && \
    wget https://download.java.net/java/GA/jdk${JDKVERSION}/c28985cbf10d4e648e4004050f8781aa/11/GPL/openjdk-${JDKVERSION}_linux-${jdkArch}_bin.tar.gz && \
    tar --extract --verbose --file=openjdk-${JDKVERSION}_linux-${jdkArch}_bin.tar.gz --directory=/usr/local && \
    rm ./openjdk-${JDKVERSION}_linux-${jdkArch}_bin.tar.gz

# Make java binaries globally available
ENV JAVA_HOME="/usr/local/jdk-${JDKVERSION}"
ENV PATH="$JAVA_HOME/bin:$PATH"

# Copy sourcecode
COPY . .

# Make devops scripts executable
RUN chmod +x ./devops/*.sh
