FROM node:8-alpine

RUN apk add --update

ADD . / frontend/
WORKDIR /frontend

ENV PATH /frontend/node_modules/.bin:$PATH

RUN npm install --silent

CMD ["npm", "start"]

EXPOSE 3000
