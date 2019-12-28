
alert("hey");
function validateForm(guideBridge) {
    alert("called");
    console.log("hey");
	var som = guide[0].guide1[0].guideRootPanel[0].firstName[0];
    var eligibilityAmountComp = guideBridge.resolveNode(som);

	//alert(eligibilityAmountComp.value);
}