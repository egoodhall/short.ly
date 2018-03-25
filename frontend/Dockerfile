# Guide here:
# https://github.com/KyleAMathews/docker-nginx

# Build docker file
# docker build -t CONTAINERNAME .

# Build from this repo's image
FROM nginx:1.13.8-alpine

# Example if you wanna swap the default server file.
COPY ./nginx.conf /etc/nginx/nginx.conf

# Add src.
COPY ./build /var/www

CMD 'nginx'