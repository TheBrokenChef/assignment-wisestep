# assignment-wisestep
A link shortener app using java spring boot for backend and react for frontend

Server side code has been written in java with spring boot and in-memory h2 database is used to store the links.
Client side code has been written in javascript and react.

User can give the link in the placeholder and click on the generate button, which will give the short link with the validity of five minutes.
Original link is shortened using hashfunction to keep the short link unique I've added time along with the orginal link and sent it to hashing function.
user can access the orignal link using the short link, when user clickes short link it will be redirected to original link based on the validity of the short link.

frontend makes a call to backend using axios.
