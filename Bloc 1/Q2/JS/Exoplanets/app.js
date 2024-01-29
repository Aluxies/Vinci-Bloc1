const express = require('express');
const createError = require('http-errors');
const path = require('path');
const logger = require('morgan');
const hbs = require('hbs');

hbs.registerHelper('exists', function (variable, options) {
  if (typeof variable !== 'undefined') {
    return options.fn(this);
  }
  else {
    // options.inverse == else block
    return options.inverse(this);
  }
});

hbs.registerHelper('eq', function (a, b) {
  if (a === b) {
    return true;
  }
  else {
    return false;
  }
});

const session = require('express-session');

const indexRouter = require("./routes/index.js");
const exoplanetsRouter = require("./routes/exoplanets.js");
const exolunesRouter = require("./routes/exolunes.js");
const forumRouter = require("./routes/forum.js");
const usersRouter = require("./routes/users.js");
const membersRouter = require("./routes/members.js");
const adminRouter = require("./routes/admin.js");

const app = express();
const port = 3002;

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

app.use(logger('dev'));
app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));

app.use(session({secret: "Your secret key", resave: false, saveUninitialized:false}));

app.use(function (req, res, next) {
  res.locals.session = req.session;
  next();
});

app.use("/", indexRouter);
app.use("/exoplanets", exoplanetsRouter);
app.use("/exolunes", exolunesRouter);
app.use("/forum", forumRouter);
app.use("/users", usersRouter);
app.use("/members", membersRouter);
app.use("/admin", adminRouter);

app.use((req, res, next) => next(createError(404)));

app.use((error, req, res) => {
  res.status(error.status || 500);
  res.render('error', { error });
});

app.listen(port, () => console.log('App listening on port ' + port));