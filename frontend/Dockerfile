FROM ubuntu:latest
USER root
WORKDIR /app
COPY package.json .
RUN apt-get update
RUN apt-get -y install curl gnupg
RUN curl -fsSL https://deb.nodesource.com/setup_16.x | bash -
RUN apt-get -y install nodejs
RUN apt-get install -y build-essential
RUN npm install --legacy-peer-deps
COPY . .
CMD ["npm", "start"]
EXPOSE 8082
# docker build -t gatling_tool .
# docker run -it -p 8082:3000 gatling_tool



