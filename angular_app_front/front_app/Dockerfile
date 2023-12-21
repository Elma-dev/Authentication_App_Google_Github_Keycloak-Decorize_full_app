FROM node:20-alpine as build
# Create app directory
WORKDIR /usr/local/app
#Add Source code to app directory
COPY .. /usr/local/app
# Install app dependencies
RUN npm install --force
# Build app
RUN npm run build

#Use Nginx to serve the app
FROM nginx:latest
#Copy the build output to replace the default nginx contents.
COPY --from=build /usr/local/app/dist/front_app/browser /usr/share/nginx/html
#Expose port 80
EXPOSE 80
