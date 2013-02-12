var http = require('http');
var s = http.createServer(function(req, res) {
	res.write('hello\n');
	setTimeout(function() {
		res.end('world\n');
	}, 2000);
});

s.listen(8000);
