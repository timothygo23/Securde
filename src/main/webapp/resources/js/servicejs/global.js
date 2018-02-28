/*
 * Script that has global functions
 */

function encode(string){
	let s = string+"";
	return s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
}