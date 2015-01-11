function follow(toUserID){
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	myAjax("/follow/add", {toUserID:toUserID});
}