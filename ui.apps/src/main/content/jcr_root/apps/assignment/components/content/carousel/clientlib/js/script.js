function prev() {
 	var previous=$('.cmp-carousel__item.cmp-carousel__item--active').prev();
    if(previous.length == 0){
		previous=$('.cmp-carousel__item.cmp-carousel__item--active').next();
    }
	$('.cmp-carousel__item.cmp-carousel__item--active').removeClass('cmp-carousel__item--active');
    $(previous).addClass('cmp-carousel__item--active')
}

function next(){
	var nextSlide=$('.cmp-carousel__item.cmp-carousel__item--active').next();
    if(nextSlide.length == 0){
		nextSlide=$('.cmp-carousel__item.cmp-carousel__item--active').prev();
    }
	$('.cmp-carousel__item.cmp-carousel__item--active').removeClass('cmp-carousel__item--active');
    $(nextSlide).addClass('cmp-carousel__item--active')
}