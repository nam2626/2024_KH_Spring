import {
	ClassicEditor,
	AccessibilityHelp,
	Autosave,
	Bold,
	Code,
	CodeBlock,
	Essentials,
	FontBackgroundColor,
	FontColor,
	FontFamily,
	FontSize,
	ImageBlock,
	ImageInsert,
	ImageInsertViaUrl,
	ImageResize,
	ImageToolbar,
	ImageUpload,
	Italic,
	Link,
	List,
	Paragraph,
	SelectAll,
	SimpleUploadAdapter,
	Strikethrough,
	Subscript,
	Table,
	TableCaption,
	TableCellProperties,
	TableColumnResize,
	TableProperties,
	TableToolbar,
	Underline,
	Undo
} from './ckeditor5/ckeditor5.js';
import UploadAdapter from './UploadAdapter.js';
import translations from './ckeditor5/translations/ko.js';

function uploadAdaterPlugin(editor){
	editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
		return new UploadAdapter(loader);
	}
}

const editorConfig = {
	toolbar: {
		items: [
			'undo',
			'redo',
			'|',
			'selectAll',
			'|',
			'fontSize',
			'fontFamily',
			'fontColor',
			'fontBackgroundColor',
			'|',
			'bold',
			'italic',
			'underline',
			'strikethrough',
			'subscript',
			'code',
			'|',
			'link',
			'insertImage',
			'insertTable',
			'codeBlock',
			'|',
			'bulletedList',
			'numberedList',
			'|',
			'accessibilityHelp'
		],
		shouldNotGroupWhenFull: true
	},
	plugins: [
		AccessibilityHelp,
		Autosave,
		Bold,
		Code,
		CodeBlock,
		Essentials,
		FontBackgroundColor,
		FontColor,
		FontFamily,
		FontSize,
		ImageBlock,
		ImageInsert,
		ImageInsertViaUrl,
		ImageResize,
		ImageToolbar,
		ImageUpload,
		Italic,
		Link,
		List,
		Paragraph,
		SelectAll,
		SimpleUploadAdapter,
		Strikethrough,
		Subscript,
		Table,
		TableCaption,
		TableCellProperties,
		TableColumnResize,
		TableProperties,
		TableToolbar,
		Underline,
		Undo
	],
	fontFamily: {
		supportAllValues: true
	},
	fontSize: {
		options: [10, 12, 14, 'default', 18, 20, 22],
		supportAllValues: true
	},
	image: {
		toolbar: ['imageTextAlternative', '|', 'resizeImage']
	},
	initialData: '',
	language: 'ko',
	link: {
		addTargetToExternalLinks: true,
		defaultProtocol: 'https://',
		decorators: {
			toggleDownloadable: {
				mode: 'manual',
				label: 'Downloadable',
				attributes: {
					download: 'file'
				}
			}
		}
	},
	placeholder: '내용을 입력하세요',
	table: {
		contentToolbar: ['tableColumn', 'tableRow', 'mergeTableCells', 'tableProperties', 'tableCellProperties']
	},
	translations: [translations],
	extraPlugins:[uploadAdaterPlugin]
};

console.log(myEditor,"main.js");
ClassicEditor.create(document.querySelector('#editor'), editorConfig)
.then((editor) => {
	myEditor = editor;
});



