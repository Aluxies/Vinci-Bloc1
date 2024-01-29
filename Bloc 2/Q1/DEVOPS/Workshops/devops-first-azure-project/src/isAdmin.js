function isAdmin(credentials) {
  return credentials === "sudo";
}

module.exports = { isAdmin };
