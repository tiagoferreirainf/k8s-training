FROM alpine:3.14.0

RUN apk upgrade --update && \
    apk add --update dos2unix bash

RUN mkdir -p /initcontainer
COPY wait-for-it.sh /initcontainer/wait-for-it.sh

RUN dos2unix /initcontainer/wait-for-it.sh
RUN chmod 777 /initcontainer/wait-for-it.sh