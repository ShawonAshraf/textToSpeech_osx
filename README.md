# textToSpeech_osx

This program uses the shell command say (available by default in OS X).


It actually passes a command line string to the bash shell, as ("say " + "your_message")
and the exec method in Java makes it work.
