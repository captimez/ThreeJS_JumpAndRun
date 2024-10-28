var express = require('express');
var router = express.Router();

import UserController from '../controller/userController';

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

router.post('/:id/save', (req,res,next) => {
  UserController.handleUserSave(req,res);
});

module.exports = router;
