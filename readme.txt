ADVANCED PROGRAMMING PROJECT -STICK HERO- README FILE
-----------------------------------------

Group Members-
 -Tejus Madan R.No 2022540
 -Vaibhav Chopra R.No 2022552

Description
-----------
The two design patterns used are -

			-Singleton - For the character. Only one instance, skins are changed for character change
			-Factory - Used to organize and encapsulate the creation of the sound players. They are created using one Musicfactory.(interface is Gamemusic)

The button to the left of the play button is to switch characters. This opens a pop-up change skin menu. The currently selected character is the highlighted one(right). You can toggle
between characters using mouse presses. To close the menu use the red dot on the right corner. Only two characters have been added at the moment.

The character sprites have been animated (15 frame idle and run animations). The bandanna fluttering and ruffling of hair is realistic and animated using keyframes. The animations are 
very subtle and might go unnoticed due to the small size of the characters. Keyframed images are in idle and running folders.

Design nugget - A little stick hero stands on top of the title on the homescreen.  

Cherries are replaced with diamonds in this game. (They serve the same purpose)


                                                                     How to play
                                                                    -------------
						                         -Hold Spacebar to extend stick for as long as you want
						                         -Double click the down arrow key to flip character
						                         -Double click the up arrow key to flip it back

                                                                      Objective
								                                     -----------
						                         -Extend stick to get to the next block. New blocks keep appearing.
						                         -Get the diamonds using flip. Diamonds help revive the character
					                             if it falls down.(specifically 3 diamonds)


There are sound effects and a background track in the game. The background sound can be muted using the mute button (which is animated, A cross appears when it is unmuted and disappears
when you press the button to mute it). The SFX however Still work when you press mute.

The home button acts as a pause button and a back to home button.

Diamonds are like coins in Subway Surfers. Amount collectd is saved in a file called numdiamonds. Score is a per game concept. When you press revive the score is automatically retrieved
if you have the required number of diamonds(otherwise a popup shows up). 

JDK version 21 used
We have defined 4 junit tests

