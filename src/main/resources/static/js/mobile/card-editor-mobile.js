$(document).ready(function () {
    // Fabric.js Canvas 초기화
    const canvas = new fabric.Canvas('fabric-canvas', {
        backgroundColor: '#ffffff',
    });

    // 캔버스 크기를 부모 컨테이너 크기에 맞추기
    function resizeCanvas() {
        const canvasContainer = $('.ratio');
        const containerWidth = canvasContainer.width();
        const containerHeight = canvasContainer.height();
        canvas.setWidth(containerWidth);
        canvas.setHeight(containerHeight);
        canvas.renderAll();
    }

    // 초기 캔버스 크기 설정
    resizeCanvas();

    // 화면 크기 변경 이벤트 핸들러
    $(window).on('resize', resizeCanvas);

    // 말씀 텍스트 추가 예시
    function addBibleText(text) {
        const bibleText = new fabric.Textbox(text, {
            left: 10,
            top: 10,
            fontSize: 20,
            fill: '#000',
            fontFamily: 'Arial',
            width: canvas.getWidth() - 20,
            editable: true,
        });
        canvas.add(bibleText);
        canvas.setActiveObject(bibleText);
    }

    addBibleText('Enter your Bible verse here');
});
