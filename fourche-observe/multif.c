#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <assert.h>

typedef int (*func_t) (char *);

#define true_argc "true"
#define false_argc "false"


int testfunc(char *arg)
{
  int true_res = strncmp(true_argc, arg, (strlen(true_argc)));
  int false_res = strncmp(false_argc, arg, (strlen(false_argc)));
  if (true_res == 0) {
    exit(EXIT_SUCCESS);
  } else if (false_res == 0)
  {
    exit(EXIT_FAILURE);
  }
    exit(EXIT_FAILURE);
}

void verify_args(int argc, char **argv)
{
  int i;
  for (i=1;i<argc;i++)
  {
    int true_res = strncmp(true_argc, argv[i], (strlen(true_argc)));
    int false_res = strncmp(false_argc, argv[i], (strlen(false_argc)));
    assert(true_res == 0 || false_res == 0);
  }
}

func_t *create_funcs(int nb_functions)
{
  int i;
  int (**functions) (char *);

  functions = (func_t*) malloc(nb_functions * sizeof(func_t));

  for(i=0;i<nb_functions;i++) {
    functions[i] = &testfunc;
  }
  return functions;
}

/*
f[] the array of func_t pointers to testfunc
n the length of f[]
*/
int multif (func_t f[], char *args[], int n)
{
  f = f;
  args = args;
  n = n;
  return 0;
}

int main(int argc, char **argv)
{
  func_t *funcs;

  verify_args(argc, argv);
  funcs = create_funcs(argc-1);
  return multif(funcs, argv, argc);
}
