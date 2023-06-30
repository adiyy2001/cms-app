const express = require('express');
const client = require('./connection.js');

const app = express();
const port = 3000;

app.listen(port, () => {
  console.log(`Server is listening at port ${port}...`);
});

app.get('/countries', (req, res) => {
  try {
    console.log('A user just requested a list of countries');

    const query = 'SELECT * FROM cmsappdb.Country';
    const result = client.query(query);
    
    console.log(result);
    res.send(result.rows);
  } catch (err) {
    console.error(err.message);
    res.status(500).send('Internal Server Error');
  }
});
