$(document).ready(function(){
	if($('.lateral-menu').length > 0) {
		$('.lateral-menu').perfectScrollbar();
	}

	if($('.home-cadastro form select').length > 0) {
		$('.home-cadastro form select').change(function(){
			if($(this).val() != "") {
				$(this).addClass('hasValue');
			} else {
				$(this).removeClass('hasValue');
			}
		});
	}

	if($('.each-escolhe-inicial').length > 0) {
		$('.each-escolhe-inicial').click(function(){
			$('.each-escolhe-inicial').removeClass('current');
			$(this).addClass('current');
			$('#inicialEscolhido').val($(this).parent().attr('href').replace("#", ""));
			$("#chooseEscolhido").removeClass("disabled");
			$("#nomeEscolhido").removeAttr("disabled");
			$("#nomeEscolhido").focus();
			return false;
		});
	}

	if($(".battle-screen").length > 0) {
		$("#attackButton").click(function(event) {
			attack();
			$(".battle-screen .actions .button-4 button").removeClass("disabled");
		});
		$("#captureButton").click(function(event) {
			tryCapture();
		});
		$("#runButton").click(function(event) {
			runFromBattle();
		});
		$("#acceptButton").click(function(event) {
			window.location = 'menu.jsp';
		});
		$("#submitButton").click(function(event) {
			capturou();
		});
	}

	function attack() {
		var nomeEnemy = $(".enemy-stats .name").text();
		var nomeAlly = $(".ally-stats .name").text();
		var maxHpEnemy = $(".enemy-stats .maxHp").text();
		var maxHpAlly = $(".ally-stats .maxHp").text();
		var currentHpEnemy = $(".enemy-stats .currentHp").text();
		var currentHpAlly = $(".ally-stats .currentHp").text();

		$(".actions button").addClass('disabled');

		$.ajax({
			url: 'BatalhaAtaca',
			type: 'POST',
			dataType: 'json',
		})
		.done(function(r) {
			console.log(r);

			/*
			 * Ally ataca
			*/
			str = '';
			str += '<div class="each-log">';
			str += '<span class="enemy">'+nomeEnemy+'</span>';
			str += ' recebeu <strong>'+r.danoAoInimigo+'<strong> de dano!';
			str += '</div>';
			$(".lateral-menu .log-holder").append(str);

			$(".battle-screen .ally-image").css("background-position-x", "70%");
			$(".battle-screen .ally-image").css("background-position-y", "100%");
			window.setTimeout(function(){
				$(".battle-screen .ally-image").css("background-position-x", "50%");
				$(".battle-screen .ally-image").css("background-position-y", "120%");
			}, 130);

			newHpEnemy = currentHpEnemy - r.danoAoInimigo;
			porcHp = 100 * newHpEnemy / maxHpEnemy;
			$(".enemy-stats .progress-bar").css("width", porcHp+"%");
			var i = setInterval(function(){
				if(newHpEnemy == currentHpEnemy) {
					clearInterval(i);
				} else {
					currentHpEnemy--;
					$(".enemy-stats .currentHp").text(currentHpEnemy);
				}
			}, 10);

			if(r.matou) {
				$(".lateral-menu .log-holder").append('<div class="separator">---</div>');
				str = '';
				str += '<div class="each-log">';
				str += '<span class="enemy">'+nomeEnemy+'</span>';
				str += ' foi derrotado!';
				str += ' <span class="ally">'+nomeAlly+'</span>';
				str += ' ganhou '+r.expGanha+' de exp.!';
				str += '</div>';
				$(".lateral-menu .log-holder").append(str);

				if(r.upou > 0) {
					$(".lateral-menu .log-holder").append('<div class="separator">---</div>');
					str = '';
					str += '<div class="each-log">';
					str += ' <span class="ally">'+nomeAlly+'</span>';
					str += ' evoluiu para o nvl. '+r.upou;
					str += '</div>';
					$(".lateral-menu .log-holder").append(str);
				}

				mostraAcceptButton();
				return false;
			}

			/*
			 * Enemy ataca
			*/
			var espera = window.setTimeout(function(){
				str = '';
				str += '<div class="each-log">';
				str += '<span class="ally">'+nomeAlly+'</span>';
				str += ' recebeu <strong>'+r.danoAoAliado+'<strong> de dano!';
				str += '</div>';
				$(".lateral-menu .log-holder").append(str);

				$(".battle-screen .enemy-image").css("background-position-x", "30%");
				$(".battle-screen .enemy-image").css("background-position-y", "100%");
				window.setTimeout(function(){
					$(".battle-screen .enemy-image").css("background-position-x", "50%");
					$(".battle-screen .enemy-image").css("background-position-y", "85%");
				}, 150);

				newHpAlly = currentHpAlly - r.danoAoAliado;
				porcHp = 100 * newHpAlly / maxHpAlly;
				$(".ally-stats .progress-bar").css("width", porcHp+"%");
				var i = setInterval(function(){
					if(newHpAlly == currentHpAlly) {
						clearInterval(i);
					} else {
						currentHpAlly--;
						$(".ally-stats .currentHp").text(currentHpAlly);
					}
				}, 10);

				if(r.morreu) {
					$(".lateral-menu .log-holder").append('<div class="separator">---</div>');
					str = '';
					str += '<div class="each-log">';
					str += '<span class="ally">'+nomeAlly+'</span>';
					str += ' foi derrotado!';
					str += '</div>';
					$(".lateral-menu .log-holder").append(str);

					mostraAcceptButton();
					return false;
				}

				$(".lateral-menu .log-holder").append('<div class="separator">---</div>');
				$(".actions button").removeClass('disabled');
			}, 500);
		});
	}

	function tryCapture() {
		var nomeEnemy = $(".enemy-stats .name").text();

		$(".actions button").addClass('disabled');
		
		$.ajax({
			url: 'BatalhaCaptura',
			type: 'POST',
			dataType: 'json',
		})
		.done(function(r) {
			console.log(r);
			
			str = '';
			str += '<div class="each-log">';
			str += 'Tentando capturar';
			str += ' <span class="enemy">'+nomeEnemy+'</span>';
			str += '...';
			str += '</div>';
			$(".lateral-menu .log-holder").append(str);
			
			var c = 0;
			var i = setInterval(function(){
				$(".battle-screen .enemy-image").css("background-position-y", "65%");
				window.setTimeout(function(){
					$(".battle-screen .enemy-image").css("background-position-y", "85%");
				}, 150);
				c++;
				if(c >= 3) {
					clearInterval(i);
				}
			}, 300);
			
			window.setTimeout(function(){
				if(!r.capturou) {
					str = '';
					str += '<div class="each-log">';
					str += 'Falhou!';
					str += '</div>';
					$(".lateral-menu .log-holder").append(str);

					$(".actions button").removeClass('disabled');
					$(".battle-screen .actions .button-3 button").addClass("disabled");				
				} else {
					str = '';
					str += '<div class="each-log">';
					str += 'Sucesso!';
					str += '</div>';
					$(".lateral-menu .log-holder").append(str);

					$(".battle-screen .actions").hide();
					$(".battle-screen .submit-name").show();
				}
				$(".lateral-menu .log-holder").append('<div class="separator">---</div>');
			}, 1200);
		});
	}
	
	function capturou() {
		$.ajax({
			url: 'BatalhaCapturou',
			type: 'POST',
			dataType: 'json',
			data: {
				nomeMonstro: $("#nameCapturado").val()
			}
		})
		.done(function(r) {
			console.log(r);	
			str = '';
			str += '<div class="each-log">';
			str += '<span class="ally">'+r.nomeCapturado+'</span>';
			str += ' foi adicionado ao seu time!';
			str += '</div>';
			$(".lateral-menu .log-holder").append(str);

			$(".battle-screen .actions").show();
			$(".battle-screen .submit-name").hide();
			mostraAcceptButton();
			return false;
		});
	}

	function runFromBattle() {
		var prct = Math.floor((Math.random() * 100));
		var nomeAlly = $(".ally-stats .name").text();

		if(prct > 40) {
			str = '';
			str += '<div class="each-log">';
			str += '<span class="ally">'+nomeAlly+'</span>';
			str += ' fugiu!';
			str += '</div>';
			$(".lateral-menu .log-holder").append(str);
			$(".lateral-menu .log-holder").append('<div class="separator">---</div>');

			mostraAcceptButton();
			return false;
		} else {
			str = '';
			str += '<div class="each-log">';
			str += '<span class="ally">'+nomeAlly+'</span>';
			str += ' falhou em fugir!';
			str += '</div>';
			$(".lateral-menu .log-holder").append(str);
			$(".lateral-menu .log-holder").append('<div class="separator">---</div>');

			$(".battle-screen .actions .button-4 button").addClass("disabled");
		}
	}

	function mostraAcceptButton() {
		$(".battle-screen .actions .button-1").hide();
		$(".battle-screen .actions .button-2").hide();
		$(".battle-screen .actions .button-3").hide();
		$(".battle-screen .actions .button-4").hide();
		$(".battle-screen .actions .button-5").show();
		$(".battle-screen .actions .button-5 button").removeClass("disabled");
	}
});