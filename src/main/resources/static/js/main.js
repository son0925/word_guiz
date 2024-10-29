// 화면 전환 함수
function showSection(sectionId) {
    $('.content-section').hide(); // 모든 섹션 숨기기
    $('#' + sectionId).show(); // 선택한 섹션만 표시
}

// 단어 리스트 로드 함수
function loadWordList() {
    $.ajax({
        type: 'GET',
        url: '/word/list',
        success: function (response) {
            const $container = $('#wordListContainer');
            $container.empty(); // 기존 리스트 초기화

            if (response.length === 0) {
                $container.append('<li>등록된 단어가 없습니다.</li>');
                return;
            }

            response.forEach(function (word) {
                const $li = $('<li></li>');
                const $wordText = $('<span></span>').text(word.word).css({
                    'font-weight': 'bold',
                    'margin-right': '10px'
                });
                const $meanText = $('<span></span>').text(`(${word.mean})`);
                const $actions = $('<div class="word-actions"></div>');
                const $deleteButton = $('<button>삭제</button>').on('click', function () {
                    deleteWord(word.id);
                });
                $actions.append($deleteButton);
                $li.append($wordText).append($meanText).append($actions);
                $container.append($li);
            });
        },
        error: function (error) {
            $('#wordListContainer').append('<li>단어 리스트 불러오기 실패</li>');
        }
    });
}

// 단어 퀴즈 로드 함수
let quizWords = [];  // 퀴즈 단어 리스트
let currentQuizIndex = 0;  // 현재 퀴즈 인덱스
const maxQuizzes = 20;  // 최대 퀴즈 수
let wrongAnswers = [];  // 틀린 답변을 저장할 배열

function loadQuizList() {
    $.ajax({
        type: 'GET',
        url: '/statistics/get_quiz',
        success: function (response) {
            // 단어 리스트를 랜덤으로 섞기
            quizWords = shuffleArray(response);
            if (quizWords.length > maxQuizzes) {
                quizWords = quizWords.slice(0, maxQuizzes);  // 최대 20개로 제한
            }
            currentQuizIndex = 0;  // 퀴즈 인덱스 초기화
            displayQuizWord();  // 첫 번째 단어 표시
            wrongAnswers = [];  // 틀린 답변 배열 초기화
        },
        error: function (error) {
            $('#quizListContainer').append('<li>퀴즈 불러오기 실패</li>');
        }
    });
}

// 랜덤 배열 셔플 함수
function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
}

// 퀴즈 단어 표시 함수
function displayQuizWord() {
    if (currentQuizIndex < quizWords.length) {
        const currentWord = quizWords[currentQuizIndex];
        $('#quizWord').text(currentWord.word);  // 단어 표시
        $('#userAnswer').val('');  // 입력 필드 초기화
        $('#resultMessage').text('');  // 결과 메시지 초기화
    } else {
        displayWrongAnswers(); // 퀴즈가 끝나면 틀린 답변 표시
    }
}

// 정답 제출 함수
$('#submitAnswer').on('click', function () {
    const userAnswer = $('#userAnswer').val().trim();
    const currentWord = quizWords[currentQuizIndex];

    // 정답 체크
    if (userAnswer.toLowerCase() === currentWord.mean.toLowerCase()) {
        $('#resultMessage').text('정답입니다!').css('color', 'green');
    } else {
        $('#resultMessage').text('오답입니다. 정답은: ' + currentWord.mean).css('color', 'red');
        // 틀린 답변 배열에 추가 (사용자 입력도 포함)
        wrongAnswers.push({ word: currentWord.word, mean: currentWord.mean, userAnswer: userAnswer });
    }

    currentQuizIndex++;  // 다음 인덱스로 이동
    setTimeout(displayQuizWord, 2000);  // 2초 후 다음 단어 표시
});

// Enter 키로 제출
$('#userAnswer').on('keydown', function (event) {
    if (event.key === 'Enter') {
        event.preventDefault(); // 기본 Enter 키 동작 방지
        $('#submitAnswer').click(); // 버튼 클릭
    }
});

// 틀린 답변 표시 함수
function displayWrongAnswers() {
    let wrongAnswersHtml = '<p>틀린 답변 목록:</p><ul>';
    wrongAnswers.forEach(answer => {
        wrongAnswersHtml += `<li>입력한 답: "${answer.userAnswer}" - 단어: "${answer.word}" - 정답: "${answer.mean}"</li>`;
    });
    wrongAnswersHtml += '</ul>';
    $('#quizContainer').html(wrongAnswersHtml); // 결과를 표시할 요소 업데이트
}

// 단어 삭제 함수
function deleteWord(wordId) {
    $.ajax({
        type: 'DELETE',
        url: '/word/delete/' + wordId,
        success: function () {
            alert('단어가 삭제되었습니다.');
            loadWordList(); // 삭제 후 리스트 갱신
        },
        error: function (error) {
            alert('단어 삭제 실패: ' + JSON.stringify(error));
        }
    });
}

// 단어 추가 함수
$('#addWordForm').on('submit', function (event) {
    event.preventDefault(); // 폼 기본 동작 중단

    const newWord = $('#newWord').val();
    const newMean = $('#newMean').val();
    $.ajax({
        type: 'POST',
        url: '/word/add',
        contentType: 'application/json',
        data: JSON.stringify({ word: newWord, mean: newMean }),
        success: function () {
            alert('단어가 추가되었습니다.');
            $('#newWord').val(''); // 입력 필드 초기화
            $('#newMean').val(''); // 입력 필드 초기화
        },
        error: function (error) {
            alert('단어 추가 실패: ' + JSON.stringify(error));
        }
    });
});

// 로그아웃 처리 함수
function logout() {
    $.ajax({
        type: 'POST',
        url: '/user/logout',
        success: function () {
            alert('로그아웃 되었습니다.');
            window.location.href = '/';
        },
        error: function (error) {
            alert('로그아웃 실패: ' + JSON.stringify(error));
        }
    });
}

// 페이지 로드 시 기본 화면 설정
$(document).ready(function () {
    showSection('addWord');
});
