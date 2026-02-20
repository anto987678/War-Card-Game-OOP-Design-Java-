//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int count = 0;
        int minimum_moves = Integer.MAX_VALUE;
        int maximum_moves = Integer.MIN_VALUE;
        long total_moves = 0;

        while (count < 10) {
            count++;
            Deck deck = new Deck();

            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    PlayingCard newCard = new PlayingCard(rank, suit);
                    deck.addCard(newCard);
                }
            }

            Deck[] players = new Deck[4];
            for (int i = 0; i < 4; i++) {
                players[i] = new Deck();
            }

            deck.shuffle();

            for (int i = 0; i < 52; i++) {
                PlayingCard newCard = deck.removeCard();
                players[i % 4].addCard(newCard);
            }

            int no_rounds = 0;
            int[] win_rounds = new int[4];
            int steps = 0;

            for (int i = 0; i < 4; i++) win_rounds[i] = 0;

            while (true) {
                no_rounds++;

                PlayingCard[] playedCards = new PlayingCard[4];
                playedCards[0] = players[0].removeCard();
                playedCards[1] = players[1].removeCard();
                playedCards[2] = players[2].removeCard();
                playedCards[3] = players[3].removeCard();

                int ok = 1;
                for (int i = 0; i < 4; i++) {
                    if (playedCards[i] == null) { ok = 0; break; }
                }
                if (ok == 0) break;

                int position_player = 0;
                int maximum = -1;
                boolean war = false;

                for (int i = 0; i < 4; i++) {
                    int v = playedCards[i].getRank().getValue();
                    if (v > maximum) {
                        maximum = v;
                        position_player = i;
                        war = false;
                    } else if (v == maximum) {
                        war = true;
                    }
                }

                if (!war) {
                    win_rounds[position_player]++;
                    steps++;
                    for (int i = 0; i < 4; i++) {
                        players[position_player].addCard(playedCards[i]);
                    }
                } else {
                    int[] war_players = new int[4];
                    int no = 0;
                    for (int i = 0; i < 4; i++) {
                        if (maximum == playedCards[i].getRank().getValue()) {
                            war_players[no++] = i;
                        }
                    }

                    PlayingCard[] pot = new PlayingCard[52];
                    int potSize = 0;

                    for (int i = 0; i < 4; i++) {
                        pot[potSize++] = playedCards[i];
                    }

                    boolean close = false;

                    while (!close && no > 1) {
                        int survivors = 0;
                        int[] next = new int[4];
                        steps++;

                        for (int i = 0; i < no; i++) {
                            int player = war_players[i];
                            PlayingCard downCard = players[player].removeCard();
                            if (downCard == null) {
                                close = true;
                                break;
                            }

                            pot[potSize++] = downCard;
                            next[survivors++] = player;
                        }
                        if (close) {
                            break;
                        }

                        for (int t = 0; t < survivors; t++) war_players[t] = next[t];
                        no = survivors;
                        if (no <= 1) break;

                        PlayingCard[] ups = new PlayingCard[4];
                        int[] ownerCards = new int[4];
                        int pos = 0;
                        for (int i = 0; i < no; i++) {
                            int player = war_players[i];
                            PlayingCard upCard = players[player].removeCard();
                            if (upCard == null) { close = true; break; }
                            pot[potSize++] = upCard;
                            ups[pos] = upCard;
                            ownerCards[pos++] = player;
                        }
                        if (close) break;

                        int bestCard = -1;
                        for (int i = 0; i < pos; i++) {
                            int v = ups[i].getRank().getValue();
                            if (v > bestCard) bestCard = v;
                        }

                        no = 0;
                        for (int i = 0; i < pos; i++) {
                            if (ups[i].getRank().getValue() == bestCard) {
                                war_players[no++] = ownerCards[i];
                            }
                        }
                    }

                    if (close) {
                        break;
                    } else {
                        int warWinner;
                        if (no == 1) {
                            warWinner = war_players[0];
                        } else {
                            warWinner = position_player;
                        }

                        win_rounds[warWinner]++;
                        for (int i = 0; i < potSize; i++) {
                            players[warWinner].addCard(pot[i]);
                        }
                    }
                }
            }

            if (steps < minimum_moves) {
                minimum_moves = steps;
            }
            if (steps > maximum_moves){
                maximum_moves = steps;
            }

            total_moves += steps;
        }

        double mean_moves = total_moves / 10.0;

        System.out.println("Minimum moves: " + minimum_moves);
        System.out.println("Maximum moves: " + maximum_moves);
        System.out.println("Mean moves: " + mean_moves);
    }
}
