    FROM node:22

    WORKDIR /react_app

    COPY package.json .

    FROM node:22
    WORKDIR /react_app
    COPY package.json .
    RUN npm install
        COPY . ./
    EXPOSE 5000
    CMD [ “npm”, “start” ]RUN npm install

    COPY . ./

    EXPOSE 5000

    CMD [ “npm”, “start” ]