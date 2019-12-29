
function validateForm(firstName,lastName) {
    console.log(firstName + " " + lastName );
    if(firstName != null && lastName != null) {
	 $.ajax({
          url: "/bin/MySql?req=validate",
          method:'GET',
          data: {
                 'firstName': firstName,
              'lastName': lastName
          },
          success: function(resp){
              console.log(resp.data);
              if(resp.data.length != 0) {
                  console.log("herre11");
				return false;
              }
              else{
                  console.log("herre2");
                  return true;
              }

       	  }
     });
    }
    else {
        console.log("here3");
		return true;

    }

}