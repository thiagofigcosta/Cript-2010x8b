CC=g++
SRC=$(wildcard*.c)
OBJ= $(SRC:.c=.o)
EXEC=2010x8b
CFLAGS=-c -Wall  
LDFLAGS=

all: $(SCR) $(EXEC)

$(EXEC): $(OBJ)
	$(CC) $(LDFLAGS) $(OBJ) -o $@

.c.o:
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -rf *.o $(EXEC)

run: all
	./$(EXEC)

install: all
	@mv $(EXEC) $(bindir)/

rebuild: clean all
