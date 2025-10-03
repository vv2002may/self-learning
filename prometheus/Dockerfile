FROM node:18

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

RUN npx tsc -b

COPY . .

EXPOSE 3000
CMD [ "node", "dist/index.js" ]