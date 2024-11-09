
FROM nginx:latest


COPY vish.html /usr/share/nginx/html/vish.html
COPY sud.css /usr/share/nginx/html/sud.css
COPY yash.js /usr/share/nginx/html/yash.js


