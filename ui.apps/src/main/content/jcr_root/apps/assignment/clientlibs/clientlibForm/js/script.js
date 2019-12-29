alert("hello");
function validateForm(firstName,lastName) {
    alert("hello");
    console.log(firstName + " " + lastName );
	 $.ajax({
          url: "/bin/MySql?req=validate",
          method:'GET',
          data: {
                 'firstName': firstName,
              'lastName': lastName
          },
          success: function(resp){
				console.log(resp);

       		}
     });
}