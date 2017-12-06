<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 12/3/2017
  Time: 3:42 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<!-- saved from url=(0066)file:///H:/fall2017/411/bootstrap-daterangepicker-master/demo.html -->

<label for="picker">pick a date </label>

<li>

<input type="text" name="daterange"  id="picker"/>
</li>
<script type="text/javascript">
    var disabledArr;
    disabledArr = <%=rhc.getDates(houseId)%>;

    $(function() {
        $('input[name="daterange"]').daterangepicker({
            minDate: <%=rhc.getminDate()%> ,
            isInvalidDate: function(arg){

                // Prepare the date comparision
                var thisMonth = arg._d.getMonth()+1;   // Months are 0 based
                if (thisMonth<10){
                    thisMonth = "0"+thisMonth; // Leading 0
                }
                var thisDate = arg._d.getDate();
                if (thisDate<10){
                    thisDate = "0"+thisDate; // Leading 0
                }
                var thisYear = arg._d.getYear()+1900;   // Years are 1900 based

                var thisCompare = thisMonth +"/"+ thisDate +"/"+ thisYear;

                if($.inArray(thisCompare,disabledArr)!=-1){
                    return true;
                }
            },
        });
    });

    $("#picker").on("apply.daterangepicker",function(e,picker){

        // Get the selected bound dates.
        var startDate = picker.startDate.format('MM/DD/YYYY');
        var endDate = picker.endDate.format('MM/DD/YYYY');
        console.log(startDate+" to "+endDate);

        // Compare the dates again.
        var clearInput = false;
        for(i=0;i<disabledArr.length;i++){
            if( compare(disabledArr[i],startDate)&&compare(endDate,disabledArr[i]) ){
                clearInput = true;
            }
        }


        // If a disabled date is in between the bounds, clear the range.
        if(clearInput){

            // To clear selected range (on the calendar).
            var today = new Date();
            $(this).data('daterangepicker').setStartDate(today);
            $(this).data('daterangepicker').setEndDate(today);

            // To clear input field and keep calendar opened.
            $(this).val("").focus();

            // Alert user!
            alert("Your range selection includes disabled dates!");
        }
    });
    function checkV() {
        var start = $("#picker").val();
        var starts = start.split("-");
        for(i=0;i<disabledArr.length;i++){
            if( compare(disabledArr[i],starts[0])&&compare(starts[1],disabledArr[i]) ){
                alert("Your range selection includes disabled dates!");
                return false;

            }
        }

        return true;

    }

    function compare(start, end){
        start = start.replace(" ","");
        end = end.replace(" ","");
        var sts = start.valueOf().split("/");
        var ens = end.valueOf().split("/");
        var nes ="";
        var nen = "";
        nes+=(sts[2])+(sts[0])+(sts[1]);
        nen+=(ens[2])+(ens[0])+(ens[1]);
        return (nes>=nen);
    }



</script>
