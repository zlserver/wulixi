/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#000000';
	config.toolbar= [
		         	   ['Paste'],
		         	   ["Bold","Italic","Underline"],
		         	   ["NumberedLisst","BulletedList","-","JustifyLeft","JustifyCenter","JustifyRight","JustfyBlock"],
		         	   ["Image"],
		         	   ["Styles","Format","Font","FontSize"],
		         	   ["TextColor","BGColor"]
	];
	
	config.filebrowserUploadUrl="control/news/uploadImage.action";
	var pathName = window.document.location.pathname;
	    //获取带"/"的项目名，如：/uimcardprj
	    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	config.filebrowserImageUploadUrl = projectName+'/control/news/uploadImage.action'; //固定路径
	
};
