FROM node:8-alpine

RUN apk add --update

COPY . ./frontend
WORKDIR ./frontend

ENV PATH /frontend/node_modules/.bin:$PATH

COPY package.json /app/package.json
RUN npm install --silent
RUN npm install react-scripts@3.0.1 -g --silent

CMD ["npm", "start"]

EXPOSE 3000
