$(document).ready(function () {
     var i = 0;
     $('#category-list-display,#category-list').hover(function () {
          $('#category-list').removeClass('hidden');
     }, function () {
          $('#category-list').addClass('hidden');
     }
     );

     $.getJSON("index", "null", function (result) {
          var json = eval(result);
          var div = $("#recommend .row").html();
          $("#recommend").html('');
          var top = $('#Top').html();
          $('#Top').html('');
          // console.log(json);
          for (var i = 0; i < 8; i++) {

               if (i % 4 == 0) {
                    $("#recommend").append("<div class=\"row\"></div>");
               }
               $("#recommend .row:last").append(div);
          }
          var x = 0;
          var type;
          $.each(json, function (i, element) {

               var images = new Array();
               images = (this.image || "").split("&");

               if (x < 8) {
                    $("#recommend .row div a").eq(x).attr('href', "shop.html?id=" + this.id);
                    $("#recommend .row div img").eq(x).attr('src', images[0]);
                    $("#recommend .row div .title").eq(x).html(this.title)
                    $("#recommend .row div .price").eq(x).html("￥" + this.price);
               } else if (x < 12) {
                    $("#Top").append(top);
                    $("#Top>div a:last").attr('href', "shop.html?id=" + this.id);
                    $("#Top>div h3:last").html("Top" + (x - 7));
                    $("#Top>div img:last").attr('src', images[0]);
                    $("#Top>div p:last").html("总销量：" + this.number);
               } else {
                    type = this;
                    var arr = new Array();
                    arr = type.split('&');
                    for (var i = 1; i < arr.length; i++) {
                         $("#category-list ul").append("<li><a href=\"\"></a></li>");
                         $("#category-list ul li:last a").html(arr[i]);
                         $("#category-list ul li:last a").attr("href", "select.html?type=" + escape(arr[i]));
                    }
               }
               x++;
          });
     });

})
