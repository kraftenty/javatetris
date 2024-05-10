package org.nl.javatetris.game.play;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.nl.javatetris.config.manager.FontManager;
import org.nl.javatetris.game.tetromino.Tetromino;
import org.nl.javatetris.settings.Settings;

import java.util.List;

import static javafx.scene.paint.Color.*;
import static org.nl.javatetris.config.constant.ModelConst.*;
public abstract class GamePlayView {

    // Board 를 화면에 표시하는 메서드
    protected void drawBoard(Pane pane, double layoutY, double layoutX, boolean isGamePlaying, Board board, Boolean isBlinking) {
        List<Integer> completedLines = board.releaseCompletedLines();
        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                // 셀 생성
                Rectangle cell = new Rectangle(
                        layoutX + x * Settings.getInstance().getSizeSetting().getBlockSize(),
                        layoutY + y * Settings.getInstance().getSizeSetting().getBlockSize(),
                        Settings.getInstance().getSizeSetting().getBlockSize(),
                        Settings.getInstance().getSizeSetting().getBlockSize()
                );
                cell.setStroke(Color.rgb(128, 128, 128, 0.5)); // cell border
                int cellValue = board.getValueAt(y, x);
                if (isGamePlaying) {
                    // 게임 플레이 중일 경우
                    cell.setFill(getColorOfCell(cellValue));
                    // 지울 줄이 포함된 경우, 흰색으로 깜빡이는 효과
                    if (completedLines.contains(y) && cellValue != BORDER) {
                        isBlinking = true;
                        blinkLine(cell, cellValue);
                        isBlinking = false;
                    }
                    // 문자가 들어가는 셀의 경우, 문자 그리기
                    if (cellValue == E || cellValue == N || cellValue == B || cellValue == V || (cellValue > 10 && cellValue < 20)) {
                        drawCharacterOnBlock(
                                pane,
                                layoutY + (y + 1) * Settings.getInstance().getSizeSetting().getBlockSize(), layoutX + (x + 0.1) * Settings.getInstance().getSizeSetting().getBlockSize(), (char) cellValue,
                                1.5
                        );
                    }
                } else {
                    // 게임 오버일 경우
                    if (cellValue == EMPTY) {
                        cell.setFill(rgb(255, 255, 255, 0.3)); // 비어있는 셀의 색상
                    } else {
                        cell.setFill(BLACK); // 활성화된 테트로미노의 색상
                    }
                }
                pane.getChildren().add(cell);
            }
        }
    }

    // 흰색으로 깜빡이는 효과를 주는 메서드
    private void blinkLine(Rectangle cell, int originalCellValue) {
        Timeline blinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), e -> cell.setFill(getColorOfCell(CLEAR))),
                new KeyFrame(Duration.seconds(0.2), e -> {
                    cell.setFill(getColorOfCell(originalCellValue));
                })
        );
        blinkTimeline.setCycleCount(1);  // 한 번 실행
        blinkTimeline.play();
    }

    // 깜빡이는 GAME OVER 텍스트 표시 메서드
    protected void drawBlinkingGameOver(Pane pane, double layoutY, double layoutX, EventHandler<ActionEvent> eventHandler, String showText) {
        Text text = new Text(showText);
        text.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        text.setFill(Color.RED);
        text.setLayoutY(layoutY);
        text.setLayoutX(layoutX);

        // 문구를 pane 에 추가
        pane.getChildren().add(text);

        // 깜빡임 효과를 위한 Timeline 생성
        Timeline blinkTimeline = new Timeline(new KeyFrame(Duration.seconds(0.25), e -> {
            text.setVisible(!text.isVisible());
        }));

        blinkTimeline.setCycleCount(8);
        blinkTimeline.setOnFinished(eventHandler);
        blinkTimeline.play();
    }

    // 점수 표시 메서드
    protected void drawPoint(Pane pane, double layoutY, double layoutX, int point, String showText) {
        Text scoreText = new Text( showText + "\n" + point);
        scoreText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        scoreText.setFill(Color.WHITE);
        scoreText.setLayoutY(layoutY);
        scoreText.setLayoutX(layoutX);
        pane.getChildren().add(scoreText);
    }

    // 레벨 표시 메서드
    protected void drawLevel(Pane pane, double layoutY, double layoutX, int level, String showText) {
        Text levelText = new Text( showText + "\n" + level);
        levelText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        levelText.setFill(Color.WHITE);
        levelText.setLayoutY(layoutY);
        levelText.setLayoutX(layoutX);
        pane.getChildren().add(levelText);
    }

    // 다음 테트로미노 표시 메서드
    protected void drawPreview(Pane pane, double layoutY, double layoutX, Tetromino nextTetromino) {
        // NEXT 텍스트
        Text nextText = new Text("next");
        nextText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        nextText.setFill(Color.CYAN);
        nextText.setLayoutY(layoutY);
        nextText.setLayoutX(layoutX);
        pane.getChildren().add(nextText);

        // 다음 테트로미노 프리뷰
        int[][] shape = nextTetromino.getShape();
        for (int y = 0; y < nextTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < nextTetromino.getShapeWidth(); x++) {
                int cellValue = shape[y][x];
                if (cellValue != EMPTY) {
                    // 각 셀 그리기
                    Rectangle previewCell = new Rectangle(
                            layoutX + x * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            layoutY + Settings.getInstance().getSizeSetting().getBlockSize() + y * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            Settings.getInstance().getSizeSetting().getPreviewBlockSize()
                    );
                    previewCell.setFill(getColorOfCell(cellValue)); // Cell color
                    previewCell.setStroke(Color.LIGHTGRAY); // Border color
                    pane.getChildren().add(previewCell);

                    // 각 셀의 문자 그리기
                    if (cellValue == E || cellValue == N || cellValue == B || cellValue == V) {
                        drawCharacterOnBlock(
                                pane,
                                layoutY + Settings.getInstance().getSizeSetting().getBlockSize() + (y + 1) * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                                layoutX + (x + 0.1) * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                                (char) cellValue,
                                1.1
                        );
                    }
                }
            }
        }
    }

    protected void drawTimeLimit(Pane pane, double layoutY, double layoutX, int timeLimit, String showText) {
        Text timeText = new Text( showText + "\n" + timeLimit );
        timeText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        timeText.setFill(Color.WHITE);
        timeText.setLayoutY(layoutY);
        timeText.setLayoutX(layoutX);
        pane.getChildren().add(timeText);
    }

    // TODO
    protected void drawAttackLinesPreview(Pane pane, double layoutY, double layoutX, List<LineDTO> attackLines) {
        double blockSize = Settings.getInstance().getSizeSetting().getPreviewBlockSize() / 2.25; // 축소된 블록 크기 설정
        int boardHeight = 20; // 가정한 보드의 세로 줄 수
        int startLine = boardHeight - attackLines.size(); // 시작 줄 위치 결정

        // 전체 보드를 그리는 반복문
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < X_MAX-2; x++) {
                Rectangle cell = new Rectangle(
                        layoutX + x * blockSize,
                        layoutY + y * blockSize,
                        blockSize,
                        blockSize
                );
                cell.setStroke(Color.LIGHTGRAY); // 테두리 색상 설정

                // 현재 줄이 공격 라인을 포함하는지 확인
                if (y >= startLine) {
                    List<Integer> line = attackLines.get(y - startLine).getLine();
                    cell.setFill(line.get(x) == 1 ? RED : TRANSPARENT); // 셀 색상 설정 (1이면 빨강, 0이면 투명)
                } else {
                    cell.setFill(Color.TRANSPARENT); // 공격 라인이 없는 부분은 투명으로 처리
                }

                pane.getChildren().add(cell); // pane에 셀 추가
            }
        }
    }

    protected Color getColorOfCell(int cellValue) {
        return switch (cellValue) {
            // 보드 기본
            case BORDER -> BLACK;
            case CLEAR -> Color.WHITE;
            // 테트로미노
            case I -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(I);
            case J -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(J);
            case L -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(L);
            case O -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(O);
            case S -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(S);
            case T -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(T);
            case Z -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(Z);
            // 아이템 테트로미노
            case N, B, V, E -> TRANSPARENT;
            case W -> DARKGRAY;
            // 기본 배경
            default -> Color.rgb(255, 255, 255, 0.3);
        };
    }

    protected void drawCharacterOnBlock(Pane pane, double layoutY, double layoutX, char character, double fontScale) {
        Text text = new Text(String.valueOf(character));
        text.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getDefaultFontSize() * fontScale));
        text.setFill(Color.RED);
        text.setLayoutY(layoutY);
        text.setLayoutX(layoutX);
        pane.getChildren().add(text);
    }
}
