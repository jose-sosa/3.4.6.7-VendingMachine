var totalIn = parseFloat(0.00);
var itemBlock1 = $("#item1"), itemBlock2 = $("#item2"),itemBlock3 = $("#item3"),itemBlock4 = $("#item4"),itemBlock5 = $("#item5"),
    itemBlock6 = $("#item6"),itemBlock7 = $("#item7"),itemBlock8 = $("#item8"),itemBlock9 = $("#item9");
var itemSelectorArray = [itemBlock1, itemBlock2, itemBlock3, itemBlock4, itemBlock5,itemBlock6,itemBlock7,itemBlock8,itemBlock9];

$(document).ready(function () {

    loadItems();  

    $('#addDollar').click(function (event) {
        updateMonetaryDisplay("1.00");
    });
    $('#addQuarter').click(function (event) {
        updateMonetaryDisplay("0.25");
    });
    $('#addDime').click(function (event) {
        updateMonetaryDisplay("0.10");
    });
    $('#addNickel').click(function (event) {
        updateMonetaryDisplay("0.05");
    });

    

    $('#makePurchase').click(function (event) {
        console.log($("#itemByNumber").val(), "WOOF WOOF");
        var temp = parseInt($('#itemByNumber').val());
        console.log($("#itemByNumber").val(), "WOOF WOOF");
        var temp1 = itemSelectorArray[temp-1].children(".item-price").html();
        var temp2 = parseInt(itemSelectorArray[temp-1].children(".item-quant").html());
        console.log(temp1, "MEOW MEOW");

        // if(temp1 > totalIn){
        //     console.log(totalIn);

        //     $('#messageDisplay').text("Insufficient Funds");


        // }
        // else if(temp2<1){
        //     $('#messageDisplay').text("Item Not In Stock");
        // }
        // else{
            var temp3 = parseFloat(temp1) * -1;
        //     updateItems(temp);
        //     loadItems();
            
        //     updateMonetaryDisplay(temp3);

        // }

        makePurchase(temp,temp3);
        loadItems();
       
        
        
    });

    $('#returnChange').click(function (event) {
        $('#messageDisplay').text('');
        $('#changeDisplay').text(totalIn);
        totalIn = parseFloat(0.00);
        $('#displayTotal').text('');
        $('#messageDisplay').text('');
    });
});

function loadItems() {

    $.each(itemSelectorArray, function(index, itemB){

    });

    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function (data, status) {
            $.each(data, function (index, vend) {
                
                itemSelectorArray[vend.id-1].children(".item-name").text(vend.name);
                itemSelectorArray[vend.id-1].children(".item-price").text(vend.price).val(vend.price);
                itemSelectorArray[vend.id-1].children(".item-quant").text(vend.quantity);
                 
            });
        },
        error: function() {
            $('#vendingHead').text("ERROR LOADING VENDING MACHINE");
               
        }
    });
}

function updateMonetaryDisplay(temp){
    console.log(temp);
   //console.log($("#totalIn").val(), "IS it working?");
   console.log(totalIn);
   var addMoney = totalIn;
   console.log(parseFloat(addMoney));
   console.log(temp,"WOOF");
   addMoney += parseFloat(temp);
   console.log(addMoney); 
   totalIn=addMoney;
   console.log(totalIn);
   $("#displayTotal").text(addMoney.toFixed(2));
   //console.log(displayTotal);
};

function makePurchase(temp,temp3){
    $.ajax ({
        type: 'GET',
            url: 'http://localhost:8080/money/'+ totalIn + '/item/' + temp,
            success: function (data, status) {
                var messageDis = "";
                console.log(data);
                messageDis += "Quarters: " + data.quarters + "| Dimes: " + data.dimes + "| Nickels:  " + data.nickels + "| Pennies: " + data.pennies;
                // $.each(data, function (index, str) {
                //     messageDis+= str;
                // });
                $("#changeDisplay").html(messageDis);
                $('#messageDisplay').text("Thank You!");
                updateMonetaryDisplay(temp3);
                    
              
            },
            error: function(errorVariable) {
                console.log(errorVariable);
                $('#messageDisplay').text(errorVariable.responseJSON.message);
                
            }
    })
    


}
