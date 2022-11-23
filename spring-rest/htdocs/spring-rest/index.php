<?PHP
header('Access-Control-Allow-Origin: *');
?>
<html>
<!-- <script src="./exchangeRate.js"></script> -->

<head>
  <h3>Conversion</h3>
  <hr>
</head>

<body>
  <form action="" method="get" enctype="multipart/form-data" onsubmit="return false">
    <label for="from">From:</label>
    <select id="from" name="from">
      <option value="usd">USD</option>
      <option value="bdt">BDT</option>
    </select>
    <label for="to">To:</label>
    <select id="to" name="to">
      <option value="bdt">BDT</option>
      <option value="usd">USD</option>
    </select>
    <input type="text" id='amount' name="amount" autofocus>

    <button name="convert" id='convert'>Convert</button>
  </form>
  <br>
  <p id="result"></p>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
  $(document).ready(function() {
    $('#convert').click(function() {
      var fromCurrency = document.getElementById('from').value;
      var toCurrency = document.getElementById('to').value;
      var apiUrl = `http://localhost:8081/spring_webmvc_war_exploded/hello/currency/from/${fromCurrency}/to/${toCurrency}`;

      $.ajax({
        url: apiUrl,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        type: "GET",
        /* or type:"PUT" */
        dataType: "json",
        data: {},
        success: function(result) {
          console.log(fromCurrency, toCurrency)
          console.log(result)
          var rate = result.rate
          if (rate == 0) {
            document.getElementById('result').innerHTML = `Invalid ${fromCurrency} to ${toCurrency} conversion!`;
            return;
          }

          var amount = document.getElementById('amount').value;
          var converted = amount * rate;
          document.getElementById('result').innerHTML = converted;
        },
        error: function() {
          console.log("error");
        }
      })
    })
  })
</script>

</html>