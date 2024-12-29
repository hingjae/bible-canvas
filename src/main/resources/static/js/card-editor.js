$(document).ready(function () {
    const canvas = new fabric.Canvas('fabric-canvas', {
        backgroundColor: '#ffffff',
    });

    let selectedTextbox = null; // 현재 선택된 텍스트 박스를 추적하는 변수
    let guideLines = []; // 점선 가이드 라인을 저장할 배열

    // 초기 위치를 저장할 변수
    let initialPositions = {
        bibleVerse: { left: 100, top: 220 },  // 예시로 Bible Verse의 초기 위치
        chapterVerse: { left: 100, top: 400 }  // 예시로 ChapterVerse의 초기 위치
    };

    // 위치 초기화 버튼 클릭 이벤트
    $('#place-init').on('click', function () {
        // 선택된 텍스트 박스 해제
        canvas.discardActiveObject();  // 선택된 객체를 해제

        // Bible Verse 텍스트 박스를 초기 위치로 이동
        const bibleVerse = canvas.getObjects().find(obj => obj.text === verseText);
        if (bibleVerse) {
            bibleVerse.set({
                left: initialPositions.bibleVerse.left,
                top: initialPositions.bibleVerse.top
            });
        }

        // Chapter Verse 텍스트 박스를 초기 위치로 이동
        const chapterVerse = canvas.getObjects().find(obj => obj.text === titleChapterVerse);
        if (chapterVerse) {
            chapterVerse.set({
                left: initialPositions.chapterVerse.left,
                top: initialPositions.chapterVerse.top
            });
        }

        canvas.renderAll();  // 캔버스를 다시 렌더링하여 변경 사항 반영
    });

    // 말씀 텍스트 추가 함수
    function addBibleVerse(verse) {
        const bibleVerse = new fabric.Textbox(verse, {
            left: 100,
            fontSize: 24,
            fill: '#000000',
            fontFamily: 'Arial',
            width: 600,
            editable: true,
            textAlign: 'center', // 수평 중앙 정렬
        });
        // 수직 중앙 정렬
        const topPosition = (canvas.height - bibleVerse.height) / 2;
        bibleVerse.top = topPosition;
        console.log(canvas.height - 100);
        canvas.add(bibleVerse);

        bibleVerse.on('selected', function () {
            selectedTextbox = bibleVerse;
            updateEditorControls(selectedTextbox); // 선택된 텍스트 박스에 맞춰 편집기 컨트롤 업데이트
        });

        bibleVerse.on('moving', function () {
            showGuides(bibleVerse);
        });
    }

    // 말씀 장 절 추가 함수
    function addChapterAndVerse(titleChapterVerse) {
        const chapterAndVerse = new fabric.Textbox(titleChapterVerse, {
            left: 100,
            top: canvas.height - 100, // 아래쪽에 위치
            fontSize: 20,
            fill: '#555555',
            fontFamily: 'Arial',
            width: 600,
            editable: true,
            textAlign: 'center', // 수평 중앙 정렬
        });
        canvas.add(chapterAndVerse);

        chapterAndVerse.on('selected', function () {
            selectedTextbox = chapterAndVerse;
            updateEditorControls(selectedTextbox); // 선택된 텍스트 박스에 맞춰 편집기 컨트롤 업데이트
        });

        chapterAndVerse.on('moving', function () {
            showGuides(chapterAndVerse);
        });
    }

    // Hidden input values 가져오기
    const verseText = $('input[name="content"]').val() || 'Enter your Bible verse here';
    const titleChapterVerse = $('input[name="titleChapterVerse"]').val() || 'Book Chapter:Verse';

    // 캔버스에 말씀과 장 절 추가
    if (verseText && titleChapterVerse) {
        addBibleVerse(verseText);
        addChapterAndVerse(titleChapterVerse);
    } else {
        console.error("Bible verse content or titleChapterVerse is missing!");
    }

    // 폰트 색상 변경 이벤트
    $('#font-color').on('input', function () {
        if (selectedTextbox) {
            selectedTextbox.set('fill', $(this).val());
            canvas.renderAll();
        }
    });

    // 폰트 크기 변경 이벤트
    $('#font-size').on('input', function () {
        if (selectedTextbox) {
            selectedTextbox.set('fontSize', parseInt($(this).val()));
            canvas.renderAll();
        }
    });

    // 폰트 스타일 변경 이벤트
    $('#font-family').on('change', function () {
        if (selectedTextbox) {
            selectedTextbox.set('fontFamily', $(this).val());
            canvas.renderAll();
        }
    });

    // 텍스트 중앙 정렬 함수
    function centerText() {
        if (selectedTextbox) {
            // 수평 중앙 정렬
            selectedTextbox.set('left', (canvas.width - selectedTextbox.width) / 2);

            // 수직 중앙 정렬
            selectedTextbox.set('top', (canvas.height - selectedTextbox.height) / 2);

            canvas.renderAll();
        }
    }

    // 중앙 정렬 버튼을 눌렀을 때
    $('#center-text').on('click', function () {
        centerText();
    });

    // Update the editor controls when a textbox is selected
    function updateEditorControls(textbox) {
        // 폰트 색상 업데이트
        $('#font-color').val(textbox.fill);

        // 폰트 크기 업데이트
        $('#font-size').val(textbox.fontSize);

        // 폰트 스타일 업데이트
        $('#font-family').val(textbox.fontFamily);
    }

    // 점선 가이드 라인 표시 함수
    function showGuides(textbox) {
        // 기존 점선 가이드 라인 제거
        guideLines.forEach(function (line) {
            canvas.remove(line);
        });
        guideLines = [];

        // 가로로 이동 시 수직 가이드라인
        if (textbox.left + textbox.width / 2 > canvas.width / 2 - 10 && textbox.left + textbox.width / 2 < canvas.width / 2 + 10) {
            const verticalGuide = new fabric.Line([canvas.width / 2, 0, canvas.width / 2, canvas.height], {
                stroke: '#ff0000',
                strokeWidth: 1,
                selectable: false,
                evented: false,
                strokeDashArray: [5, 5],
            });
            canvas.add(verticalGuide);
            guideLines.push(verticalGuide);
        }

        // 세로로 이동 시 수평 가이드라인
        if (textbox.top + textbox.height / 2 > canvas.height / 2 - 10 && textbox.top + textbox.height / 2 < canvas.height / 2 + 10) {
            const horizontalGuide = new fabric.Line([0, canvas.height / 2, canvas.width, canvas.height / 2], {
                stroke: '#ff0000',
                strokeWidth: 1,
                selectable: false,
                evented: false,
                strokeDashArray: [5, 5],
            });
            canvas.add(horizontalGuide);
            guideLines.push(horizontalGuide);
        }
    }

    // 마우스를 뗐을 때 가이드라인을 제거하는 이벤트
    canvas.on('mouse:up', function () {
        guideLines.forEach(function (line) {
            canvas.remove(line); // 점선 가이드 라인 제거
        });
        guideLines = []; // 가이드 라인 배열 초기화
    });
});
