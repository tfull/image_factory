BIN = bin
SRC = src
CLASSES = Color Point Function Main

compile: $(foreach x, $(CLASSES), src/$(x).scala)
	scalac -d $(BIN) $^

.PHONY: clean
clean:
	rm $(BIN)/*.class
