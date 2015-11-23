(function($, undefined) {
	$("button.index").button().click(function(event) {
		var newsid = $(event.currentTarget).data('newsid');
		event.preventDefault();
		var data = {
			'newsId' : newsid
		};
		$.ajax({
			type : "POST",
			url : window.location.pathname + '/indexit',
			'data' : data, // JSON.stringify(data),
			success : function(result) {

			},
			error : function(xhr) {

			}
		});
	});

	$("button.getkeyword").button().click(
			function(event) {
				var newsId = $(event.currentTarget).data('newsid');
				event.preventDefault();
				var data = {
					'newsId' : newsId
				};
				$
						.ajax({
							type : "POST",
							url : 'keyword',
							'data' : data,
							success : function(result) {
								var titleKeywords = '<p>标题关键字 : '
										+ JSON.stringify(result[0]) + '</p>';
								var contKeywords = '<p>正文关键字 : '
										+ JSON.stringify(result[1]) + '</p>';
								$('.newsblock .keywords').empty().append(
										titleKeywords);
								$('.newsblock .keywords').append(contKeywords);
							},
							error : function(xhr) {

							}
						});
			});

	$("button.addhotwordbtn").button().click(function(event) {
		event.preventDefault();
		var word = $(event.currentTarget).prev().val();
		if (!word) {
			return;
		}
		$.ajax({
			type : "POST",
			url : 'addHotWord',
			'data' : {
				'word' : word
			},
			success : function(result) {
				window.location.href = window.location.href;
			},
			error : function(xhr) {

			}
		});
	});

	$("button.getrelatednews")
			.button()
			.click(
					function(event) {
						var newsId = $(event.currentTarget).data('newsid');
						event.preventDefault();
						var data = {
							'newsId' : newsId
						};
						$
								.ajax({
									type : "POST",
									url : 'relatedNews',
									'data' : data,
									success : function(result) {
										// alert(result.length);
										$('.relatednewslist').empty();
										for (var i = 0; i < result.length; i++) {
											var newsItem = '<div class="relatedentry"><a href="'
													+ window.location.pathname
													+ '?newsId='
													+ result[i].id
													+ '">'
													+ result[i].textTitle
													+ '</a></div>'
											$('.relatednewslist').append(
													newsItem);
										}
									},
									error : function(xhr) {

									}
								});
					});

	$(".searchbyword").click(function(event) {
		event.preventDefault();
		$("#fakeForm input").val($(event.currentTarget).data('word'));
		$("#fakeForm").submit();
		return false;
	});

	$("#searchResultPage a.pagenum").click(
			function(event) {
				event.preventDefault();
				$($("#fakeSearchForm input")[1]).val(
						$(event.currentTarget).data('pagenum'));
				$("#fakeSearchForm").submit();
				return false;
			});
})(jQuery);