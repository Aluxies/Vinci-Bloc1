import { expect, test } from "vitest";
import { isAdmin } from "./isAdmin";

test("Should return false as user is not admin", () => {
  expect(isAdmin("user")).toBeFalsy();
});

test("Should return true as sudo is admin", () => {
  expect(isAdmin("sudo")).toBeTruthy();
});
